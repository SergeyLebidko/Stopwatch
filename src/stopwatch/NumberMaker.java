package stopwatch;

public class NumberMaker {

    //Массив с координатами отдельных сегментов цифрового табло
    private static final double[][] coordsSegment = {
            {2, 2, 3, 1, 8, 1, 9, 2, 8, 3, 3, 3},
            {2, 2.5, 3, 3.5, 3, 10.5, 2, 11.5, 1, 10.5, 1, 3.5},
            {9, 2.5, 10, 3.5, 10, 10.5, 9, 11.5, 8, 10.5, 8, 3.5},
            {2, 12, 3, 11, 8, 11, 9, 12, 8, 13, 3, 13},
            {2, 12.5, 3, 13.5, 3, 20.5, 2, 21.5, 1, 20.5, 1, 13.5},
            {9, 12.5, 10, 13.5, 10, 20.5, 9, 21.5, 8, 20.5, 8, 13.5},
            {3, 21, 8, 21, 9, 22, 8, 23, 3, 23, 2, 22}
    };

    //Перечни сегментов, входящих в каждую цифру, которую может отобразить табло
    private static final int[][] numbersSegment = {
            {0, 2, 5, 6, 4, 1},
            {2, 5},
            {0, 2, 3, 4, 6},
            {0, 2, 3, 5, 6},
            {1, 3, 2, 5},
            {0, 1, 3, 5, 6},
            {0, 1, 3, 4, 5, 6},
            {0, 2, 5},
            {0, 1, 2, 3, 4, 5, 6},
            {0, 1, 2, 3, 5, 6}
    };

    //Метод возвращает массив полигонов, соответствующих сегментам цифры val
    public static PolygonCoords[] getNumberPolygons(int val, int xStart, int yStart, int width, int height){
        if (val < 0 || val > 9) return null;
        int countSegments = numbersSegment[val].length;                //Количество сегментов необходимое для отображения числа val
        PolygonCoords[] result = new PolygonCoords[countSegments];
        double deltaX = (double) width / 11;
        double deltaY = (double) height / 24;

        //Объявляем вспомогательные переменные
        double xTmp;
        double yTmp;
        int countPointsInSegment;
        int j = 0;

        //Во внешнем цикле перебираем все сегменты, входящие в цифру val. Переменная p получает номера сегментов
        for (int p : numbersSegment[val]) {
            countPointsInSegment = coordsSegment[p].length / 2;
            int[] x = new int[countPointsInSegment];
            int[] y = new int[countPointsInSegment];

            //Во внутреннем цикле формируем координаты точек, входящих в текущий сегмент (выбранный во внешнем цикле)
            for (int i = 0; i < countPointsInSegment * 2; i += 2) {
                xTmp = coordsSegment[p][i];
                yTmp = coordsSegment[p][i + 1];
                x[i / 2] = (int) (xStart + xTmp * deltaX);
                y[i / 2] = (int) (yStart + yTmp * deltaY);
            }

            //Каждая строка массива result - это координаты очередного сегмента
            result[j] = new PolygonCoords(x, y);
            j++;
        }

        return result;
    }

}
