package dev.silas.kontext.controller.context

import dev.silas.kontext.controller.SampleController
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ReactiveAdapterRegistry

@Configuration
class Konfig {
    @Bean
    fun argumentResolver(
        factory: ConfigurableBeanFactory,
        registry: ReactiveAdapterRegistry,
    ) = SampleContextResolver(factory, registry) { exchange ->
        object : SampleController.SampleContext {
            override val foo: String
                get() =
                    exchange
                        .request
                        .headers
                        .getOrEmpty("x-sample-header")
                        .getOrElse(0) {
                            "nope"
                        }
        }
    }
}
