package org.example;
import java.util.*;

public class Kyu3 {
    public static void test(){
        System.out.println(calculateSpace(new int[][]{}));
        System.out.println(calculateSpace(new int[][]{{0, 4, 11, 6}}));
    }

    static class Event {
        int x, y0, y1, type;
        Event(int x, int y0, int y1, int type) {
            this.x = x;
            this.y0 = y0;
            this.y1 = y1;
            this.type = type;
        }
    }

    public static int calculateSpace(int[][] rectangles) {
        int n = rectangles.length;
        if (n == 0) return 0;

        List<Event> events = new ArrayList<>(2 * n);

        for (int[] r : rectangles) {
            int x0 = r[0], y0 = r[1], x1 = r[2], y1 = r[3];
            if (x0 < x1 && y0 < y1) {
                events.add(new Event(x0, y0, y1, +1));
                events.add(new Event(x1, y0, y1, -1));
            }
        }

        events.sort(Comparator.comparingInt(e -> e.x));

        List<int[]> active = new ArrayList<>();
        long area = 0;
        int prevX = events.get(0).x;

        for (int i = 0; i < events.size(); ) {
            int x = events.get(i).x;

            int width = x - prevX;
            if (width > 0) {
                long coveredY = mergedYLength(active);
                area += coveredY * width;
            }

            while (i < events.size() && events.get(i).x == x) {
                Event e = events.get(i);
                if (e.type == 1) {
                    active.add(new int[]{e.y0, e.y1});
                } else {
                    removeInterval(active, e.y0, e.y1);
                }
                i++;
            }

            prevX = x;
        }

        return (int) area;
    }

    private static long mergedYLength(List<int[]> intervals) {
        if (intervals.isEmpty()) return 0;

        intervals.sort(Comparator.comparingInt(a -> a[0]));

        long total = 0;
        int curStart = intervals.get(0)[0];
        int curEnd = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            int[] in = intervals.get(i);
            if (in[0] > curEnd) {
                total += curEnd - curStart;
                curStart = in[0];
                curEnd = in[1];
            } else {
                curEnd = Math.max(curEnd, in[1]);
            }
        }

        total += curEnd - curStart;
        return total;
    }

    private static void removeInterval(List<int[]> list, int y0, int y1) {
        for (int i = 0; i < list.size(); i++) {
            int[] in = list.get(i);
            if (in[0] == y0 && in[1] == y1) {
                list.remove(i);
                return;
            }
        }
    }
}
