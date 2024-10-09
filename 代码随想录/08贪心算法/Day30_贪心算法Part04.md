## 452. 用最少数量的箭引爆气球

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html)
视频讲解：[贪心算法，判断重叠区间问题 | LeetCode：452.用最少数量的箭引爆气球\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1SA41167xe)

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }
}
```

## 435. 无重叠区间

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0435.%E6%97%A0%E9%87%8D%E5%8F%A0%E5%8C%BA%E9%97%B4.html)
视频讲解：[贪心算法，依然是判断重叠区间 | LeetCode：435.无重叠区间\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1A14y1c7E1)

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                count++;
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return count;
    }
}
```

## 763.划分字母区间

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0763.%E5%88%92%E5%88%86%E5%AD%97%E6%AF%8D%E5%8C%BA%E9%97%B4.html)
视频讲解：[贪心算法，寻找最远的出现位置！ LeetCode：763.划分字母区间\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV18G4y1K7d5)

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        char[] charArray = s.toCharArray();

        // 记录每个字符最后出现的位置
        for (int i = 0; i < charArray.length; i++) {
            last[charArray[i] - 'a'] = i;
        }

        List<Integer> list = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < charArray.length; i++) {
            // 更新当前分区的结束位置
            end = Math.max(end, last[charArray[i] - 'a']);

            // 如果当前位置是分区的结束位置，则记录分区长度并更新 start
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1;
            }
        }

        return list;
    }
}
```
