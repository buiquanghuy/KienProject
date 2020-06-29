<?php

namespace App\Listeners;

use App\Events\NewProduct1;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Queue\InteractsWithQueue;

use Illuminate\Support\Facades\DB;

class SendEmailAfterNewProduct1
{
    /**
     * Create the event listener.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    /**
     * Handle the event.
     *
     * @param  NewProduct1  $event
     * @return void
     */
    public function handle(NewProduct1 $event)
    {
       // Tam dung 10 phut
    sleep(600);
    $fileName = $event->product->id . '.txt';
    $data = 'Sản phẩm mới tạo: ' . $event->product->name . ' với ID: ' . $event->product->id; 
    File::put(public_path('/txt/' . $fileName), $data);
    return true;
    }
}
