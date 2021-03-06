package org.springframework.boot.issues.gh1530.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.annotation.MessageLogger;
import org.springframework.boot.issues.gh1530.annotation.TransactionLogger;
import org.springframework.boot.issues.gh1530.dao.MyModelDao;
import org.springframework.boot.issues.gh1530.model.Message;
import org.springframework.boot.issues.gh1530.model.MyModel;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Example service.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Service
public class ExampleService {

    Logger logger = LoggerFactory.getLogger(ExampleService.class);

    @Autowired
    MyModelDao myModelDao;

    @Transactional
    @TransactionLogger(type = Transaction.TYPE_MY_TRANSACTION)
    @MessageLogger
    public String execute(Transaction transaction, Message requestMessage, String input) {
        logger.debug("Example service input is: {}", input);
        String s = "Hello World! Your input is: " + input;

        MyModel myModel = new MyModel(s);
        myModelDao.create(myModel);

        logger.debug("Example service return value is: {}", s);
        return s;
    }
}
