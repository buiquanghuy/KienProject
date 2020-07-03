<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

// user
Route::post('user/Login', 'UserController@Login')->name('đăng nhập nè');
Route::post('user/Register', 'UserController@Register')->name('đăng ký tài khoản nè');
Route::post('user/updateUser', 'UserController@updateUser')->name('update user nè');
Route::get('user/getget', 'UserController@getget')->name('update user nè');


// banner
Route::get('banner/getBanner', 'bannerController@getBanner')->name('getBanner');

// product
Route::get('product/getfavorite', 'ProductController@getfavorite')->name('getfavorite');
Route::get('product/getAll', 'ProductController@getAll')->name('getAll');
Route::post('product/getAllWithType', 'ProductController@getAllWithType')->name('getAllWithType');
Route::post('product/getAllWithID', 'ProductController@getAllWithID')->name('getAllWithID');
Route::get('product/getNewProduct', 'ProductController@getNewProduct')->name('getNewProduct');
Route::post('product/searchProduct', 'ProductController@searchProduct')->name('searchProduct');
Route::get('product/getTopProduct', 'ProductController@getTopProduct')->name('getTopProduct');
Route::get('product/getProductForU', 'ProductController@getProductForU')->name('getProductForU');







// product type
Route::get('productType/getProductType', 'ProductTypeController@getProductType')->name('getProductType');
// bill 
Route::post('bill/addExCart', 'BillController@addExCart')->name('addExCart');
Route::post('bill/CreateBill', 'BillController@createBill')->name('CreateBill');
Route::post('bill/changeCart', 'BillController@changeCart')->name('changeCart');
Route::post('bill/changeCart', 'BillController@changeCart')->name('changeCart');
Route::post('bill/orderCart', 'BillController@orderCart')->name('orderCart');
Route::post('bill/loadbill', 'BillController@loadbill')->name('loadbill');
Route::post('bill/getListBillDetail', 'BillController@getListBillDetail')->name('getListBillDetail');
Route::post('bill/removeCart', 'BillController@removeCart')->name('removeCart');
Route::post('bill/getlistBills', 'BillController@getlistBills')->name('getlistBills');

// Route::post('bill/loadDetailbill', 'BillController@loadDetailbill')->name('loadDetailbill');


// address
Route::post('address/loadaddress', 'AddressController@LoadAddress')->name('tải addres theo id user');
Route::post('address/updateAddress', 'AddressController@updateAddress')->name('update address nè ');
Route::post('address/deleteAddress', 'AddressController@deleteAddress')->name('delete address nè ');
Route::post('address/CreateAddress', 'AddressController@CreateAddress')->name('CreateAddress  nè ');

Route::get('tesss', 'UserController@tesss')->name('delete address nè ');





