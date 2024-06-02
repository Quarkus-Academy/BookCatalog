package com.ibm.quarkusacademy.book.sse;

import java.util.concurrent.CopyOnWriteArrayList;
import io.smallrye.mutiny.subscription.Cancellable;
import io.smallrye.mutiny.subscription.MultiEmitter;

public abstract class SseService<T> {
  // Just a thread-safe equivalent of ArrayList, holds registered emitters
  private final CopyOnWriteArrayList<MultiEmitter<? super T>> emitters =
      new CopyOnWriteArrayList<>();

  // Adds emitter to the list and adds callback to remove it later
  public Cancellable registerEmitter(MultiEmitter<? super T> emmiter) {
    emitters.add(emmiter);
    return () -> emitters.remove(emmiter);
  }

  // Make all register emitters emit given object
  public void emit(T emitObject) {
    emitters.forEach(emitter -> emitter.emit(emitObject));
  }
}
