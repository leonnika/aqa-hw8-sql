package ru.netology.test;


import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelperAPI;
import ru.netology.data.Transfer;
import ru.netology.data.UserAPI;
import ru.netology.data.UserCode;
import ru.netology.page.CardAPI;
import ru.netology.page.LoginAPI;
import ru.netology.page.TransferAPI;
import ru.netology.page.VerificationAPI;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TransferMoneyAPITest {


    @Test
    void shouldAuthorizationTransferMoneyAPI() throws SQLException {
        UserAPI user = DataHelperAPI.getUserAPI();
        LoginAPI loginAPI = new LoginAPI();
        loginAPI.jsonPartLogin(user);
        VerificationAPI verification = new VerificationAPI();
        UserCode userCode = DataHelperAPI.getVerificationCode(user);
        String token = verification.jsonPartCode(userCode);
        CardAPI listCard = new CardAPI();
        listCard.jsonPartListCard(token);
        TransferAPI transferAPI = new TransferAPI();
        Transfer transfer = DataHelperAPI.getTransferAPI();
        transferAPI.jsonPartTransfer(transfer, token);
    }
   }
