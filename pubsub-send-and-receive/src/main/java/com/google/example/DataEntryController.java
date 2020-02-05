/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.EmitterProcessor;
import com.google.gson.Gson;
/**
 * Takes strings from front-end and directs them to an internal processing queue, to be sent to Cloud Pub/Sub.
 */
@RestController
public class DataEntryController {

	@Autowired
	private EmitterProcessor<String> frontEndListener;

	@PostMapping("/sendData")
	public RedirectView mainPage(@RequestParam("data") String data, @RequestParam("attribute key") String key, @RequestParam("attribute value") String value) {
		System.out.println("Sending data: " + data);

		// Sends data into local processing queue
		this.frontEndListener.onNext(data);

		return new RedirectView("/");
	}
}
