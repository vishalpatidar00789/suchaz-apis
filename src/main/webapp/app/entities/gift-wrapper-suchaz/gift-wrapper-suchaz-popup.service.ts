import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { GiftWrapperSuchazService } from './gift-wrapper-suchaz.service';

@Injectable()
export class GiftWrapperSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private giftWrapperService: GiftWrapperSuchazService

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
                this.giftWrapperService.find(id)
                    .subscribe((giftWrapperResponse: HttpResponse<GiftWrapperSuchaz>) => {
                        const giftWrapper: GiftWrapperSuchaz = giftWrapperResponse.body;
                        this.ngbModalRef = this.giftWrapperModalRef(component, giftWrapper);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.giftWrapperModalRef(component, new GiftWrapperSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    giftWrapperModalRef(component: Component, giftWrapper: GiftWrapperSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.giftWrapper = giftWrapper;
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
