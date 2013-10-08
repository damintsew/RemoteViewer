package com.damintsev.server.v2.v3.connection;

import com.damintsev.client.devices.Station;
import com.damintsev.server.v2.v3.task.Task;
import com.damintsev.server.v2.machine.state.TaskState;
import com.damintsev.server.v2.v3.exceptions.ConnectException;
import com.damintsev.server.v2.v3.exceptions.ExecutingTaskException;

/**
 * User: Damintsev Andrey
 * Date: 06.10.13
 * Time: 16:19
 */
public abstract class Connection {

    protected abstract Connection init(Station station) throws ConnectException;
    public abstract TaskState process(Task task) throws ExecutingTaskException;
    public abstract void destroy();
    public abstract Long getId();
}