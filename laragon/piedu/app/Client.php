<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Client extends Model
{
    protected $table = 'client'; // chỉ định rõ bảng mà ORM liên kết tới là bảng gì 
    public $timestamps = false; // bỏ ghi lại thời gian nếu không khi update bằng ORM sẽ phêu
}
