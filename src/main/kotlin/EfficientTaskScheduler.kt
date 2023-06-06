class EfficientTaskScheduler(
    tasks: List<Task>, servers: List<Server>
) {
    private val tasksByResourceDesc: List<Task> = tasks.sortedByDescending { it.resourcesRequired }
    private val tasksByResourceDescPriorityAsc: List<Task> = tasksByResourceDesc.sortedBy { it.priority }
    private val serversByCapacity: List<Server> = servers.sortedBy { it.capacity }

    fun distributeTasks(): List<TaskAssignment> {
        val sortedTasks = if (canDistributeAllTasks()) {
            tasksByResourceDesc
        } else {
            tasksByResourceDescPriorityAsc
        }
        val serverCapacitiesMutable = serversByCapacity.map { it.capacity }.toMutableList()
        val taskServerMapList = mutableListOf<TaskAssignment>()
        for (task in sortedTasks) {
            val assignedServer = findAvailableServer(task, serverCapacitiesMutable, serversByCapacity)
            if (assignedServer != null) {
                taskServerMapList.add(TaskAssignment(task, assignedServer))
            }
        }

        return taskServerMapList
    }

    private fun findAvailableServer(
        task: Task, serverCapacities: MutableList<Int>, sortedServers: List<Server>
    ): Server? {
        for (i in serverCapacities.indices) {
            if (serverCapacities[i] >= task.resourcesRequired) {
                serverCapacities[i] -= task.resourcesRequired
                return sortedServers[i]
            }
        }
        return null
    }

    private fun canDistributeAllTasks(): Boolean {
        val serverCapacities = serversByCapacity.map { it.capacity }.toMutableList()

        for (task in tasksByResourceDesc) {
            if (findAvailableServer(task, serverCapacities, serversByCapacity) == null) {
                return false
            }
        }

        return true
    }
}
