## 56. 合并区间

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0056.%E5%90%88%E5%B9%B6%E5%8C%BA%E9%97%B4.html)
视频讲解：[贪心算法，合并区间有细节！LeetCode：56.合并区间\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1wx4y157nD)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // 如果区间数组为空，直接返回空数组
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // 按照区间的起始值进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        // 使用一个列表来存储合并后的区间
        List<int[]> list = new ArrayList<>();
        // 将第一个区间添加到结果中
        int[] currentInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            // 如果当前区间与上一个区间重叠
            if (intervals[i][0] <= currentInterval[1]) {
                // 合并区间，更新结束值
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            } else {
                // 如果不重叠，将当前区间添加到列表中
                list.add(currentInterval);
                currentInterval = intervals[i]; // 更新当前区间
            }
        }
        // 添加最后一个区间
        list.add(currentInterval);

        // 将列表转换为数组并返回
        return list.toArray(new int[list.size()][]);
    }
}
```

## 738.单调递增的数字

题目链接/文章讲解：[代码随想录](https://programmercarl.com/0738.%E5%8D%95%E8%B0%83%E9%80%92%E5%A2%9E%E7%9A%84%E6%95%B0%E5%AD%97.html)
视频讲解：[贪心算法，思路不难想，但代码不好写！LeetCode:738.单调自增的数字\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Kv4y1x7tP)

```java
class Solution {
    public int monotoneIncreasingDigits(int n) {
        // 将数字转为字符串，然后转为字符数组
        String s = String.valueOf(n);
        char[] charArray = s.toCharArray();
        int length = charArray.length;

        // 从倒数第二位开始检查每一位
        for (int i = length - 2; i >= 0; i--) {
            // 如果当前位大于下一位，说明不满足单调递增
            if (charArray[i] > charArray[i + 1]) {
                // 当前位减一
                charArray[i]--;
                // 从当前位的下一位开始，将后面的所有位都设置为9
                for (int j = i + 1; j < length; j++) {
                    charArray[j] = '9';
                }
            }
        }

        // 处理可能出现的前导0情况
        // 将字符数组转换为字符串后再转换为整数
        return Integer.parseInt(new String(charArray));
    }
}

```

## 总结

总结：[代码随想录](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E7%AF%87.html)
