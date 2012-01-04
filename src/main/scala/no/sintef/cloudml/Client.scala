package no.sintef.cloudml

import no.sintef.cloudml.engine.Engine

object Client {

    def main(args: Array[String])  {
        val template = """{"nodes": [{"size": "Small"},{"size": "Small"}]}"""
        val account = """{"provider":"aws-ec2", "authKeys":{"accessKey":"...", "secretKey": "..."}}"""
        val runtimeInstance = Engine.apply(account, List(template))
        println("Got instance: " + runtimeInstance)
    }
}
