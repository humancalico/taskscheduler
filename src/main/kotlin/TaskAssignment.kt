data class TaskAssignment(
    val task: Task,
    val server: Server,
) {
    override fun toString(): String {
        return "Task ID: ${task.taskId}\n" +
                "Resources Required: ${task.resourcesRequired}\n" +
                "Server ID: ${server.serverId}\n" +
                "Capacity: ${server.capacity}\n" +
                "Priority: ${task.priority}\n".trimIndent()
    }
}
