fun main() {
    val tasks = listOf(
        Task(1, 1, 1),
        Task(2, 4, 2),
        Task(3, 6, 3),
        Task(4, 9, 3),
    )


    val servers = listOf(
        Server(1, 10),
        Server(2, 10),
    )
    println("If you want to run tests see src/test/kotlin/EfficientTaskSchedulerTest.kt")
    println(EfficientTaskScheduler(tasks, servers).distributeTasks())
}
