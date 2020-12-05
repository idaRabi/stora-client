package ir.sls.stora.exception


class AssemblerQueueFullExceptions : StoraException("Assembler queue is full!")

class MetricNotFoundException(name: String) : StoraException("Metric: $name not exist.")
