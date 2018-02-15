import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { BuyLaterListSuchazService } from './buy-later-list-suchaz.service';

@Injectable()
export class BuyLaterListSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private buyLaterListService: BuyLaterListSuchazService

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
                this.buyLaterListService.find(id)
                    .subscribe((buyLaterListResponse: HttpResponse<BuyLaterListSuchaz>) => {
                        const buyLaterList: BuyLaterListSuchaz = buyLaterListResponse.body;
                        this.ngbModalRef = this.buyLaterListModalRef(component, buyLaterList);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.buyLaterListModalRef(component, new BuyLaterListSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    buyLaterListModalRef(component: Component, buyLaterList: BuyLaterListSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.buyLaterList = buyLaterList;
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
