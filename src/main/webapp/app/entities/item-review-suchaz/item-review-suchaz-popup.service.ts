import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { ItemReviewSuchazService } from './item-review-suchaz.service';

@Injectable()
export class ItemReviewSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private itemReviewService: ItemReviewSuchazService

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
                this.itemReviewService.find(id)
                    .subscribe((itemReviewResponse: HttpResponse<ItemReviewSuchaz>) => {
                        const itemReview: ItemReviewSuchaz = itemReviewResponse.body;
                        this.ngbModalRef = this.itemReviewModalRef(component, itemReview);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.itemReviewModalRef(component, new ItemReviewSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    itemReviewModalRef(component: Component, itemReview: ItemReviewSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.itemReview = itemReview;
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
