package projetoIA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class Teste {
	
	//quantidade de atributos previsores da base
	private static final int FEATURES_COUNT = 13619;
	//quantidade de classes da base
	private static final int CLASSES_COUNT = 4;
	//quantidade de linhas da base
	private static final int QTD_LINHAS_ARQUIVO = 265;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

		try {
			
			long tempoInicial = System.currentTimeMillis();
			
			int totalDeNeuronios = (int) Math.round((FEATURES_COUNT + CLASSES_COUNT)/2);
			
			System.out.println(totalDeNeuronios);
			
			RecordReader recordReader = new CSVRecordReader(0, ',');
			recordReader.initialize(new FileSplit(new ClassPathResource("base_para_ia.csv").getFile()));
			
			DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, QTD_LINHAS_ARQUIVO, FEATURES_COUNT, CLASSES_COUNT);
			DataSet allData = iterator.next();
			allData.shuffle(42); // embaralha 42 veses os registros
			
			DataNormalization normalizer = new NormalizerStandardize();
			normalizer.fit(allData);
			normalizer.transform(allData);
			
			SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
			DataSet trainingData = testAndTrain.getTrain();
			DataSet testData = testAndTrain.getTest();

			//CONFIGURA��O DA REDE NEURAL
			MultiLayerConfiguration configuration 
			  = new NeuralNetConfiguration.Builder()
			    .iterations(60) // Epocas de treino
			    .activation(Activation.RELU) //Activation.TANH
			    .weightInit(WeightInit.XAVIER)
			    .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
			    .learningRate(0.1)
			    .regularization(true).l2(0.0001)
			    .list()
			    //camada de entrada
			    .layer(0, new DenseLayer.Builder().nIn(FEATURES_COUNT).nOut(totalDeNeuronios).build())
			    //camada oculta
			    .layer(1, new DenseLayer.Builder().nIn(totalDeNeuronios).nOut(totalDeNeuronios).build())
			    .layer(2, new DenseLayer.Builder().nIn(totalDeNeuronios).nOut(totalDeNeuronios).build())
//			    .layer(3, new DenseLayer.Builder().nIn(totalDeNeuronios).nOut(totalDeNeuronios).build())

			    //camada de saida
			    .layer(3, new OutputLayer.Builder(
			      LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
			        .activation(Activation.SOFTMAX) // SOFTMAX
			        .nIn(totalDeNeuronios).nOut(CLASSES_COUNT).build())
			    .backprop(true).pretrain(false)
			    .build();
			
			
			MultiLayerNetwork model = new MultiLayerNetwork(configuration);
			model.init();
			
			//treina da rede neural
			model.fit(trainingData);
			
			//atributo preditor
			
//			INDArray input = Nd4j.zeros(1, 4);
			
			//5.9,3.0,5.1,1.8,2
			//5.9,3.2,4.8,1.8
			
//			input.putScalar(0, 0, 5.9);
//			input.putScalar(0, 1, 3.2);
//			input.putScalar(0, 2, 4.8);
//			input.putScalar(0, 3, 1.8);
//
////			DataSet dt = new DataSet(input, output);
//			List<String> list = new ArrayList<>();
//			INDArray result = model.output(input);
			
			  //Save the model
	        File locationToSave = new File("/Users/thiagoluizrodrigues/Documents/aula_de_ia/MyMultiLayerNetwork.zip");      //Where to save the network. Note: the file is in .zip format - can be opened externally
	        boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
	        ModelSerializer.writeModel(model, locationToSave, saveUpdater);
			
			//avalia do algoritmo
			INDArray output = model.output(testData.getFeatureMatrix());
			Evaluation eval = new Evaluation(3);
			eval.eval(testData.getLabels(), output);
			
			System.out.println(eval.stats());
			System.out.println(eval.confusionToString());
//			
			
			
			long tempoFinal = System.currentTimeMillis();
		    System.out.printf("%.3f segundos%n", (tempoFinal - tempoInicial) / 1000d);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
