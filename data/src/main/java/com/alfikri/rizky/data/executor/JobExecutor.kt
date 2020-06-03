package com.alfikri.rizky.data.executor

import com.alfikri.rizky.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class JobExecutor @Inject constructor() : ThreadExecutor {
    private var threadExecutor: ThreadPoolExecutor

    init {
        threadExecutor = ThreadPoolExecutor(
            3, 5, 10, TimeUnit.SECONDS,
            LinkedBlockingQueue(), JobThreadFactory()
        )
    }

    override fun execute(p0: Runnable) {
        threadExecutor.execute(p0)
    }

    companion object {
        private class JobThreadFactory : ThreadFactory {
            private var counter = 0

            override fun newThread(p0: Runnable): Thread {
                return Thread(p0, "android_${counter++}")
            }
        }
    }
}