import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OccassionSuchaz } from './occassion-suchaz.model';
import { OccassionSuchazPopupService } from './occassion-suchaz-popup.service';
import { OccassionSuchazService } from './occassion-suchaz.service';

@Component({
    selector: 'jhi-occassion-suchaz-dialog',
    templateUrl: './occassion-suchaz-dialog.component.html'
})
export class OccassionSuchazDialogComponent implements OnInit {

    occassion: OccassionSuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private occassionService: OccassionSuchazService,
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
        if (this.occassion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.occassionService.update(this.occassion));
        } else {
            this.subscribeToSaveResponse(
                this.occassionService.create(this.occassion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OccassionSuchaz>>) {
        result.subscribe((res: HttpResponse<OccassionSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: OccassionSuchaz) {
        this.eventManager.broadcast({ name: 'occassionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-occassion-suchaz-popup',
    template: ''
})
export class OccassionSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private occassionPopupService: OccassionSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.occassionPopupService
                    .open(OccassionSuchazDialogComponent as Component, params['id']);
            } else {
                this.occassionPopupService
                    .open(OccassionSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
