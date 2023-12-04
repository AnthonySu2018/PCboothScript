import java.io.IOException
import java.net.InetAddress
import java.util.concurrent.TimeUnit


val ipAddress = "192.168.10.2"
var isPingSuccess = false
var trueCounter = 0
var falseCounter = 0
fun main(args: Array<String>) {
    println("Author: Anthony")
    println("Version: 1.0")
    println("请勿关闭此窗口，缩小即可。")

    do{
        ping(ipAddress)
        isPingSuccess = ping(ipAddress)
        if(isPingSuccess)
        {
            trueCounter ++
            TimeUnit.SECONDS.sleep(5);
            println("主机连接正常，第 $trueCounter 次")
        }
        else{
            println("Ping $ipAddress: $isPingSuccess")
            TimeUnit.SECONDS.sleep(4)
            falseCounter ++
            println("尝试连接主机失败，第 $falseCounter 次")
            if(falseCounter >10)
            {
                try {
                    val command = "shutdown.exe -r -t 0" // -r 表示重启，-t 0 表示无延迟立即重启
                    val process = Runtime.getRuntime().exec(command)
                    process.waitFor()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        println("请勿关闭此窗口，缩小即可。")
    } while (true)
}


fun ping(ipAddress: String): Boolean {
    return try {
        val inet = InetAddress.getByName(ipAddress)
        inet.isReachable(1000) // 设置超时时间为5秒
    } catch (ex: Exception) {
        false
    }
}


