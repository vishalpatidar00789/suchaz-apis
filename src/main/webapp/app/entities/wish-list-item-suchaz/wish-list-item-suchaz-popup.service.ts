import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { WishListItemSuchazService } from './wish-list-item-suchaz.service';

@Injectable()
export class WishListItemSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private wishListItemService: WishListItemSuchazService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.wishListItemService.find(id)
                    .subscribe((wishListItemResponse: HttpResponse<WishListItemSuchaz>) => {
                        const wishListItem: WishListItemSuchaz = wishListItemResponse.body;
                        this.ngbModalRef = this.wishListItemModalRef(component, wishListItem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.wishListItemModalRef(component, new WishListItemSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    wishListItemModalRef(component: Component, wishListItem: WishListItemSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.wishListItem = wishListItem;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
