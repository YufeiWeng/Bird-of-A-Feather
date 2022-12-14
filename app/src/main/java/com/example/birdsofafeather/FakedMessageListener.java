package com.example.birdsofafeather;

import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FakedMessageListener extends MessageListener{
    private final MessageListener messageListener;
    public final ScheduledExecutorService executor;

    public FakedMessageListener(MessageListener realMessageListener, int frequency, String messageStr) {
        this.messageListener = realMessageListener;
        this.executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(() -> {
            Message message = new Message(messageStr.getBytes(StandardCharsets.UTF_8));
            this.messageListener.onFound(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.messageListener.onLost(message);
        }, 0, frequency, TimeUnit.SECONDS);
    }

    public void stopMessages() {
        this.executor.shutdown();
    }
}
