package no.sintef.cloudml

import no.sintef.cloudml.engine.Engine

object Client {

    def main(args: Array[String])  {
        val template = """{"nodes": [{"name": "test1", "size": "Small"},{"name": "test2", "size": "Medium"}]}"""
        val account = """{"provider":"aws-ec2", "authKeys":{"accessKey": "...", "secretKey": "..."}}"""
        val runtimeInstances = Engine(account, List(template))
        println("Got instances: ")
        runtimeInstances.foreach(instance => {
            println(instance.instance.name + ": id: " + instance.id + ". Private address: " 
              + instance.privateAddress + ". Public address: " + instance.publicAddress)
        })
        System.exit(0)
    }
}
