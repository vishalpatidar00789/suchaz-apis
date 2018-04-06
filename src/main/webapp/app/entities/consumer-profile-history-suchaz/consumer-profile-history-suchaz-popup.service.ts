import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { ConsumerProfileHistorySuchazService } from './consumer-profile-history-suchaz.service';

@Injectable()
export class ConsumerProfileHistorySuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private consumerProfileHistoryService: ConsumerProfileHistorySuchazService

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
                this.consumerProfileHistoryService.find(id)
                    .subscribe((consumerProfileHistoryResponse: HttpResponse<ConsumerProfileHistorySuchaz>) => {
                        const consumerProfileHistory: ConsumerProfileHistorySuchaz = consumerProfileHistoryResponse.body;
                        this.ngbModalRef = this.consumerProfileHistoryModalRef(component, consumerProfileHistory);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.consumerProfileHistoryModalRef(component, new ConsumerProfileHistorySuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    consumerProfileHistoryModalRef(component: Component, consumerProfileHistory: ConsumerProfileHistorySuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.consumerProfileHistory = consumerProfileHistory;
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
