import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { ConsumerProfileHistorySuchazPopupService } from './consumer-profile-history-suchaz-popup.service';
import { ConsumerProfileHistorySuchazService } from './consumer-profile-history-suchaz.service';

@Component({
    selector: 'jhi-consumer-profile-history-suchaz-dialog',
    templateUrl: './consumer-profile-history-suchaz-dialog.component.html'
})
export class ConsumerProfileHistorySuchazDialogComponent implements OnInit {

    consumerProfileHistory: ConsumerProfileHistorySuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private consumerProfileHistoryService: ConsumerProfileHistorySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.consumerProfileHistory.id !== undefined) {
            this.subscribeToSaveResponse(
                this.consumerProfileHistoryService.update(this.consumerProfileHistory));
        } else {
            this.subscribeToSaveResponse(
                this.consumerProfileHistoryService.create(this.consumerProfileHistory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ConsumerProfileHistorySuchaz>>) {
        result.subscribe((res: HttpResponse<ConsumerProfileHistorySuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ConsumerProfileHistorySuchaz) {
        this.eventManager.broadcast({ name: 'consumerProfileHistoryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-consumer-profile-history-suchaz-popup',
    template: ''
})
export class ConsumerProfileHistorySuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consumerProfileHistoryPopupService: ConsumerProfileHistorySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.consumerProfileHistoryPopupService
                    .open(ConsumerProfileHistorySuchazDialogComponent as Component, params['id']);
            } else {
                this.consumerProfileHistoryPopupService
                    .open(ConsumerProfileHistorySuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
