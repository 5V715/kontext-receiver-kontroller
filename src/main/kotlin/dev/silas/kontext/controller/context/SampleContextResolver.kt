package dev.silas.kontext.controller.context

import dev.silas.kontext.controller.SampleController
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.core.MethodParameter
import org.springframework.core.ReactiveAdapterRegistry
import org.springframework.web.reactive.result.method.annotation.AbstractNamedValueArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import kotlin.reflect.full.isSuperclassOf

class SampleContextResolver(
    factory: ConfigurableBeanFactory,
    registry: ReactiveAdapterRegistry,
    val conversion: (ServerWebExchange) -> SampleController.SampleContext,
) : AbstractNamedValueArgumentResolver(factory, registry) {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return SampleController.SampleContext::class.isSuperclassOf(parameter.parameterType.kotlin)
    }

    override fun createNamedValueInfo(parameter: MethodParameter): NamedValueInfo {
        return NamedValueInfo("callContext", !parameter.isOptional, null)
    }

    override fun resolveName(
        name: String,
        parameter: MethodParameter,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        return Mono.just(conversion(exchange))
    }
}
