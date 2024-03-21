package org.example;

import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

interface Loadable
{
    void load();
}

class NothingToUndo extends Exception{}

enum CurTypes {RUR, USD, EUR, CNY}
interface Command{
    public void perform();
}

public class Account {

    public Loadable Save() {return new Snapshot();}

    public  Object getProxy()
    {
        Class cls = this.getClass();
        return  Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{Loadable.class, Fractionable.class},
                new LoadableInvHandler(this));
    }
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        String oldState = this.getState();
        this.commands.push(()->{this.state=oldState;});
        this.state = state;
    }

    private class Snapshot implements Loadable
    {
        private String name;
        private HashMap<CurTypes, Integer> balances;

        public Snapshot ()
        {
            this.name = Account.this.name;
            this.balances = new HashMap<>(Account.this.balances);

        }
        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.balances = new HashMap<>(this.balances);
        }
    }

    private Deque<Command> commands = new ArrayDeque<>();

    private Account(){}

    public  Account undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }

    public Account(String name)
    {
        this.setName(name);
        this.balances = new HashMap<>();

    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        this.name = name;
    }

    private HashMap<CurTypes, Integer> balances;

    public HashMap<CurTypes, Integer> getCurrencies() {
        return new HashMap<CurTypes, Integer>(this.balances);
    }

    public void setBalances(CurTypes curtype, Integer amount) {
        if (amount<0) throw new IllegalArgumentException();
        if (balances.containsKey(curtype)) //если мы изменили сущ. значение
        {
            this.commands.push(()->{this.balances.put(curtype, amount);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.balances.remove(curtype);});
        }
        this.balances.put(curtype, amount);
    }

    public void printBalances()
    {
        System.out.println("******************");
        System.out.println("Client: "+this.name);
        System.out.println("Currency | Balance ");
        this.balances.keySet().stream().forEach(System.out::println );
        this.balances.values().stream().forEach(System.out::println);
    }
}