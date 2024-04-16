# Kontext Receivers Kontroller

## Explore if Spring Controller Methods can have context

Exploration if it's possible to create a Spring Controller method that works with context receivers

``` kotlin
    context(SampleContext)
    @GetMapping("/hello")
    fun greet(): ResponseEntity<String>
```