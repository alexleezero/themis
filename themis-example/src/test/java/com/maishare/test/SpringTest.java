/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test;

import com.maishare.test.spring.TestConfig;
import com.maishare.themis.container.launcher.SpringJunit5Launcher;
import com.maishare.themis.container.provider.ThemisTestCaseProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = TestConfig.class)
public class SpringTest extends SpringJunit5Launcher {
	
	
	@Override
	public void activateTestCase(ThemisTestCaseProvider themisTestCaseProvider) {
		themisTestCaseProvider.activateTestCaseIndexes("pay_case_1","replay_case_1");
	}
}
