# `The only characters you must escape are \, ", and control codes(anything less than U+0020)`
# `How to encode Unicode char: \uXXXX, where XXXX is as UTF-16 code unit.`

# Jackson
- jackson-core 核心包，提供基于”流模式”解析的相关 API，它包括 JsonParser 和 JsonGenerator。Jackson 内部实现正是通过高性能的流模式 API 的 JsonGenerator 和 JsonParser 来生成和解析 json。
- jackson-annotations 注解包，提供标准注解功能；
- jackson-databind 数据绑定包，提供基于”对象绑定” 解析的相关 API（ ObjectMapper ）和”树模型” 解析的相关 API（JsonNode）；基于”对象绑定” 解析的 API 和”树模型”解析的 API 和依赖基于”流模式”解析的 API。

# Core Classes
- com.fasterxml.jackson.databind.ObjectMapper.readTree(****)
- com.fasterxml.jackson.databind.ObjectMapper.readValue(****)
- com.fasterxml.jackson.databind.ObjectMapper.writeValue(****)
- com.fasterxml.jackson.databind.ObjectMapper.writeTree(****)
- com.fasterxml.jackson.databind.JsonNode