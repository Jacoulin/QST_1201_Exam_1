
题目2：编写MapReduce，统计`/user/hadoop/mapred_dev/ip_time` 中去重后的IP数，越节省性能越好。（35分）

---

运行完之后，描述程序里所做的优化点，每点+5分。
reduce阶段，在cleanup()函数中，最后一次性输出count,不需要在reduce判断是否统计完
