import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test;

class EfficientTaskSchedulerTest {

    @Test
    fun `with sufficient capacity`() {
        val tasks = listOf(
            Task(1, 5, 1), Task(2, 3, 2), Task(3, 2, 3)
        )
        val servers = listOf(
            Server(1, 10), Server(2, 5), Server(3, 8)
        )
        val assignments = EfficientTaskScheduler(tasks, servers).distributeTasks()
        val expectedAssignment = listOf(
            TaskAssignment(Task(1, 5, 1), Server(2, 5)),
            TaskAssignment(Task(2, 3, 2), Server(3, 8)),
            TaskAssignment(Task(3, 2, 3), Server(3, 8)),
        )
        assertEquals(expectedAssignment, assignments)
    }

    @Test
    fun `just enough`() {
        val tasks = listOf(
            Task(1, 2, 1),
            Task(2, 1, 2),
            Task(3, 9, 3),
            Task(4, 8, 4),
            Task(5, 4, 5),
        )
        val servers = listOf(
            Server(1, 10), Server(2, 12), Server(3, 2)
        )
        val assignments = EfficientTaskScheduler(tasks, servers).distributeTasks()
        val expectedAssignment = listOf(
            TaskAssignment(Task(3, 9, 3), Server(1, 10)),
            TaskAssignment(Task(4, 8, 4), Server(2, 12)),
            TaskAssignment(Task(5, 4, 5), Server(2, 12)),
            TaskAssignment(Task(1, 2, 1), Server(3, 2)),
            TaskAssignment(Task(2, 1, 2), Server(1, 10)),
        )
        assertEquals(expectedAssignment, assignments)
    }

    @Test
    fun `reject two tasks`() {
        val tasks = listOf(
            Task(1, 1, 1),
            Task(2, 4, 2),
            Task(3, 6, 3),
            Task(4, 9, 3),
            Task(5, 12, 3),
        )
        val servers = listOf(
            Server(1, 2),
            Server(2, 18),
        )
        val assignments = EfficientTaskScheduler(tasks, servers).distributeTasks()
        val expectedAssignment = listOf(
            TaskAssignment(Task(1, 1, 1), Server(1, 2)),
            TaskAssignment(Task(2, 4, 2), Server(2, 18)),
            TaskAssignment(Task(5, 12, 3), Server(2, 18)),
        )
        assertEquals(expectedAssignment, assignments)
    }
}
