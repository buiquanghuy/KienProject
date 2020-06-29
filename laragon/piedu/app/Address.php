<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Address extends Model
{
    protected $table = 'address'; // chỉ định rõ bảng mà ORM liên kết tới là bảng gì 
    public $timestamps = false; // bỏ ghi lại thời gian nếu không khi update bằng ORM sẽ phêu

    // public function client()
    // {
    //     return $this->hasOne('App\Product'); // tương đương với liên kết 1 1 
    // } 

}
