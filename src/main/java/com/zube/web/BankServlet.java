package com.zube.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zube.context.MyBankApplicationConfiguration;
import com.zube.model.Transaction;
import com.zube.service.TransactionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BankServlet extends HttpServlet {

    private TransactionService transactionService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyBankApplicationConfiguration.class);

        this.transactionService = ctx.getBean(TransactionService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(request.getRequestURI().equalsIgnoreCase("/")){

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>MyBank</h1>\n" +
                            "<p>Wečcome to our MyBank page</p>\n" +
                            "</body>\n" +
                            "</html>"
            );

        }else if(request.getRequestURI().equalsIgnoreCase("/transactions")){

            response.setContentType("application/json; charset=UTF-8");
            List<Transaction> transactions = transactionService.getTransactions();
            response.getWriter().print(objectMapper.writeValueAsString(transactions));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().equalsIgnoreCase("/transactions")){

            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String reference = request.getParameter("reference");

            Transaction transaction = transactionService.createTransaction(amount, reference);

            response.setContentType("application/json; charset=UTF-8");

            String jsonObject = objectMapper.writeValueAsString(transaction);
            response.getWriter().print(jsonObject);

        }else{

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        }

    }
}
