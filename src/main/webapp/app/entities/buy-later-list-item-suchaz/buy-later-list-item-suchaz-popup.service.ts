import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { BuyLaterListItemSuchaz } from './buy-later-list-item-suchaz.model';
import { BuyLaterListItemSuchazService } from './buy-later-list-item-suchaz.service';

@Injectable()
export class BuyLaterListItemSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private buyLaterListItemService: BuyLaterListItemSuchazService

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
                this.buyLaterListItemService.find(id)
                    .subscribe((buyLaterListItemResponse: HttpResponse<BuyLaterListItemSuchaz>) => {
                        const buyLaterListItem: BuyLaterListItemSuchaz = buyLaterListItemResponse.body;
                        this.ngbModalRef = this.buyLaterListItemModalRef(component, buyLaterListItem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.buyLaterListItemModalRef(component, new BuyLaterListItemSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    buyLaterListItemModalRef(component: Component, buyLaterListItem: BuyLaterListItemSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.buyLaterListItem = buyLaterListItem;
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
