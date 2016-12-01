题目3：编写MapReduce，统计这两个文件

`/user/hadoop/mapred_dev_double/ip_time`

`/user/hadoop/mapred_dev_double/ip_time_2`

当中，重复出现的IP的数量(40分)

---
加分项：

1. 写出程序里面考虑到的优化点，每个优化点+5分。
在map阶段，在setup函数一次性获取配置信息，不需要每次调用map()方法都进行获取，可以提高性能
在reduce阶段，在cleanup函数一次性输出最终结果
2. 额外使用Hive实现，+5分。
3. 额外使用HBase实现，+5分。
