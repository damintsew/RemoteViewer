package com.damintsev.server.v2.v3.connections;

import com.damintsev.client.v3.items.Station;
import com.damintsev.client.v3.items.task.Task;
import com.damintsev.server.v2.v3.exceptions.ConnectException;
import com.damintsev.server.v2.v3.exceptions.ExecutingTaskException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: Damintsev Andrey
 * Date: 14.10.13
 * Time: 21:55
 */
public class PingConnection extends Connection {

    private Process process;
    private InputStream is = null;

    @Override
    protected Connection init(Station station) throws ConnectException {
        return this;
    }

    @Override
    protected String process(Task task) throws ExecutingTaskException {

        try {
            process = Runtime.getRuntime().exec(task.getCommand());
            is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new ExecutingTaskException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();  //todo нужно что-то делать ?!
                }
            }
        }

    }

    @Override
    public void destroy() {
        process.destroy();
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();  //todo нужно что-то делать ?!
            }
        }
    }

//    @Override
    public Long getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}