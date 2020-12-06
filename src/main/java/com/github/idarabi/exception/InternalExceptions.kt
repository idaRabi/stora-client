package com.github.idarabi.exception


class AssemblerQueueFullExceptions : StoraException("Assembler queue is full!")

class MetricNotFoundException(name: String) : StoraException("Metric: $name not exist.")
