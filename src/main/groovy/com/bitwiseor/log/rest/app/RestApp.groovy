package com.bitwiseor.log.rest.app

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@Configuration
@ComponentScan(basePackages = "com.bitwiseor.log" )
@EnableAutoConfiguration
class RestApp {
	public static void main(String[] args) throws Exception {
		def app = new SpringApplication(RestApp)
		app.showBanner = false
		app.run(args)
	}
}