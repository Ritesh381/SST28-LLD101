## Problem
EvaluationPipeline was creating all concrete classes inside evaluate().
Because of this, pipeline depended on low-level classes directly.
It was hard to test, hard to replace one component, and changes in one class forced pipeline changes.

## Solution
added small interfaces for each dependency:
- PlagiarismCheckService
- CodeGradingService
- ReportWritingService

Then concrete classes implemented these interfaces:
- PlagiarismChecker
- CodeGrader
- ReportWriter

EvaluationPipeline now receives dependencies in constructor.
Main creates concrete objects and passes them to pipeline.
So pipeline depends on abstractions, not concrete classes.
