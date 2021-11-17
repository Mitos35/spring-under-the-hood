# spring-under-the-hood
Spring under the hood
HOMEWORK #18 – Spring under the hood
Implement automatic String trimming functionality. So if I pass  "    Hello  "  as argument to my bean’s method, or return it from the bean’s method, it gets automatically trimmed to "Hello" .
 - create annotation @Trimmed that you can put on class
 - create annotation @EnableStringTrimming that will enable automatic Spring trimming for all beans that are annotated with @Trimmed
 - create TrimmedAnnotationBeanPostProcessor  that will check for beans that are marked with @Trimmed , create a proxy of those classes, and override methods. Proxy methods should:
   - trim all String arguments
   - trim all String return values
 - extract  TrimmedAnnotationBeanPostProcessor  into a separate StringTrimmingConfiguration that is imported by @EnableStringTrimming
 - test that trimming works for returned values and for method arguments
 - test that trimming does not happen when you don’t add @EnableStringTrimming to the configuration
push your project onto GitHub and post a link as a response to this message
