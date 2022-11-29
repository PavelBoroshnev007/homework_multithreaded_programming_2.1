import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static Map.Entry<Integer,Integer> maxNumberОfRepetitions = null;
    
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                String string1 = generateRoute("RLRFR", 100);
                int lengthR = 0;
                for (int j = 0; j < string1.length(); j++ ){
                    if (string1.charAt(j) == 'R') {
                        lengthR ++;
                    }
                }
                synchronized (sizeToFreq){
                    if (sizeToFreq.containsKey(lengthR)){
                        sizeToFreq.put(lengthR, sizeToFreq.get(lengthR) + 1);
                    } else {
                        sizeToFreq.putIfAbsent(lengthR,1);
                    }
                }
            }
        }).start();
        Thread.sleep(300);
        for(Map.Entry<Integer,Integer> price:sizeToFreq.entrySet())
        {
            if (maxNumberОfRepetitions == null || price.getValue().compareTo(maxNumberОfRepetitions.getValue()) > 0)
            {
                maxNumberОfRepetitions = price;
            }
        }
        System.out.println("Самое частое количество повторений " + maxNumberОfRepetitions.getKey() + "(встретилось " + maxNumberОfRepetitions.getValue() + " раз)");
        System.out.println("Другие размеры: ");
        for (Map.Entry entry: sizeToFreq.entrySet()) {
            System.out.println(entry);
        }
    }


    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
