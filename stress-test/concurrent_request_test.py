import threading
import requests
import time

# 请求的目标地址
URL = "http://localhost:8080/api/count/increment"

# 并发线程数量
THREAD_COUNT = 1000

# 存放返回结果
results = []

# 线程执行方法
def request_counter(thread_id):
    try:
        response = requests.get(URL, timeout=5)
        print(f"[线程-{thread_id}] 响应：{response.status_code} | 返回值：{response.text}")
        results.append((thread_id, response.text))
    except requests.exceptions.RequestException as e:
        print(f"[线程-{thread_id}] 请求失败：{e}")

# 启动多个线程
def run_test():
    threads = []
    start_time = time.time()

    for i in range(THREAD_COUNT):
        t = threading.Thread(target=request_counter, args=(i,))
        threads.append(t)
        t.start()

    for t in threads:
        t.join()

    end_time = time.time()
    print(f"\n✅ 并发请求完成，共 {THREAD_COUNT} 次，用时 {end_time - start_time:.2f} 秒")

    # 排序并打印最终结果
    results.sort()
    for thread_id, count in results:
        print(f"线程-{thread_id} 收到的计数：{count}")

if __name__ == "__main__":
    run_test()
