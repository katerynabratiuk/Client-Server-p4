package com.github.katerynabratiuk;

import com.github.katerynabratiuk.communication.tcp.Client;
import com.github.katerynabratiuk.communication.tcp.StoreServerTCP;
import com.github.katerynabratiuk.communication.udp.StoreClientUDP;
import com.github.katerynabratiuk.crypto.PacketDecoder;
import com.github.katerynabratiuk.cryptoThread.Decryptor;
import com.github.katerynabratiuk.command.CommandMessage;
import com.github.katerynabratiuk.domain.Message;
import com.github.katerynabratiuk.entity.Product;
import com.github.katerynabratiuk.network.Receiver;
import com.github.katerynabratiuk.processing.Processor;
import com.github.katerynabratiuk.repository.implementation.InventoryRepositoryImpl;
import com.github.katerynabratiuk.service.implementation.InventoryServiceImpl;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App
{


    public static void main(String[] args) throws Exception {
//        BlockingQueue<byte[]> encryptedQueue = new LinkedBlockingQueue<>();
//        BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
//
//        // Receiver
//        Receiver receiver = new Receiver(encryptedQueue);
//
//        // Decoder
//        SecretKey key = receiver.getKEY(); // потрібно створити getKEY()
//        PacketDecoder decoder = new PacketDecoder(key);
//        Decryptor decryptor = new Decryptor(encryptedQueue, messageQueue, decoder);
//
//        // Processor
//        InventoryRepositoryImpl inventoryRepository = new InventoryRepositoryImpl();
//        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository); // твій сервіс
//        Processor processor = new Processor(inventoryService, messageQueue);
//        inventoryRepository.addNewProduct(new Product("chocolate bar", 7, BigDecimal.TEN));
//
//        Thread receiverThread = new Thread(receiver);
//        receiverThread.setName("Receiver thread");
//
//        Thread decryptorThread = new Thread(decryptor);
//        decryptorThread.setName("Decryptor thread");
//
//        Thread processorThread = new Thread(processor);
//        processorThread.setName("Processor thread");
//
//        receiverThread.start();
//        decryptorThread.start();
//        processorThread.start();

        int port = 8888;

        new Thread(() -> {
            StoreServerTCP server = null;
            try {
                server = new StoreServerTCP(port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            server.start();
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Client.startClients(3, "localhost", 5);


    }
}
