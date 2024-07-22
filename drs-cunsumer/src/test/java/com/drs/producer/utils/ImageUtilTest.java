package com.drs.producer.utils;

import com.drs.common.utils.ImageUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilTest {

    @Test
    void calculateSha256() throws IOException, NoSuchAlgorithmException {
        // 테스트 파일 경로 설정
        Path testFilePath = Paths.get("/Users/we/IdeaProjects/mass/test.jpg");

        // 파일에서 바이트 데이터 읽기
        byte[] fileData = ImageUtils.readFileAsBytes(testFilePath);

        // 파일 데이터의 SHA-256 해시 계산
        String actualHash = ImageUtils.calculateSha256(fileData);

        System.out.println(actualHash);

        // 예상되는 해시값
        String expectedHash = "e5ecb3e568f22544096fd3b0778d8d742edd879ef5505948b53f17207b81cf37";

        // 결과 검증
        assertEquals(expectedHash, actualHash, "The calculated hash should match the expected hash.");
    }

    @Test
    void readFileAsBytes() {
    }
}