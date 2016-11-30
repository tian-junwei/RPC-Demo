package com.tianjunwei.test.server;

import java.util.ArrayList;
import java.util.List;

import com.tianjunwei.test.client.HelloPersonService;
import com.tianjunwei.test.client.Person;
import com.tianjunwei.zknettyRpc.server.RpcService;

/**
 * Created by luxiaoxun on 2016-03-10.
 */
@RpcService(HelloPersonService.class)
public class HelloPersonServiceImpl implements HelloPersonService {

    @Override
    public List<Person> GetTestPerson(String name, int num) {
        List<Person> persons = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            persons.add(new Person(Integer.toString(i), name));
        }
        return persons;
    }
}
