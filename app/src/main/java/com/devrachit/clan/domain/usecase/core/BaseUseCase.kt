package com.devrachit.clan.domain.usecase.core

import kotlinx.coroutines.flow.Flow

/**
 * ══════════════════════════════════════════════════════════════════
 * BASE USE CASE ARCHITECTURE
 * ══════════════════════════════════════════════════════════════════
 *
 * All UseCases in the domain layer MUST implement one of these base contracts.
 *
 * 1. [BaseUseCase]               → Synchronous computation with parameters
 * 2. [BaseNoParamsUseCase]       → Synchronous computation with no parameters
 * 3. [BaseSuspendUseCase]         → Asynchronous one-shot suspend operation with parameters
 * 4. [BaseNoParamsSuspendUseCase] → Asynchronous one-shot suspend operation with no parameters
 * 5. [BaseFlowUseCase]            → Reactive data stream with parameters
 * 6. [BaseNoParamsFlowUseCase]    → Reactive data stream with no parameters
 */

/**
 * Synchronous UseCase with an input parameter.
 *
 * @param In The input parameter type.
 * @param Out The result return type.
 */
interface BaseUseCase<in In, out Out > {
    operator fun invoke(params: In): Out
}

/**
 * Synchronous UseCase with no input parameters.
 *
 * @param Out The result return type.
 */
interface BaseNoParamsUseCase<out Out> {
    operator fun invoke(): Out
}

/**
 * Asynchronous UseCase executing a one-shot suspend operation with an input parameter.
 *
 * @param In The input parameter type.
 * @param Out The result return type.
 */
interface BaseSuspendUseCase<in In, out Out> {
    suspend operator fun invoke(params: In): Out
}

/**
 * Asynchronous UseCase executing a one-shot suspend operation with no input parameters.
 *
 * @param Out The result return type.
 */
interface BaseNoParamsSuspendUseCase<out Out> {
    suspend operator fun invoke(): Out
}

/**
 * Reactive UseCase returning a continuous Flow stream with an input parameter.
 *
 * @param In The input parameter type.
 * @param Out The emitted stream item type.
 */
interface BaseFlowUseCase<in In, out Out> {
    operator fun invoke(params: In): Flow<Out>
}

/**
 * Reactive UseCase returning a continuous Flow stream with no input parameters.
 *
 * @param Out The emitted stream item type.
 */
interface BaseNoParamsFlowUseCase<out Out> {
    operator fun invoke(): Flow<Out>
}