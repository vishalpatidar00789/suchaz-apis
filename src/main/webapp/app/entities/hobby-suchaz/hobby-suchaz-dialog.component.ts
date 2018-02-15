import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { HobbySuchaz } from './hobby-suchaz.model';
import { HobbySuchazPopupService } from './hobby-suchaz-popup.service';
import { HobbySuchazService } from './hobby-suchaz.service';

@Component({
    selector: 'jhi-hobby-suchaz-dialog',
    templateUrl: './hobby-suchaz-dialog.component.html'
})
export class HobbySuchazDialogComponent implements OnInit {

    hobby: HobbySuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private hobbyService: HobbySuchazService,
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
        if (this.hobby.id !== undefined) {
            this.subscribeToSaveResponse(
                this.hobbyService.update(this.hobby));
        } else {
            this.subscribeToSaveResponse(
                this.hobbyService.create(this.hobby));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<HobbySuchaz>>) {
        result.subscribe((res: HttpResponse<HobbySuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: HobbySuchaz) {
        this.eventManager.broadcast({ name: 'hobbyListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-hobby-suchaz-popup',
    template: ''
})
export class HobbySuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private hobbyPopupService: HobbySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.hobbyPopupService
                    .open(HobbySuchazDialogComponent as Component, params['id']);
            } else {
                this.hobbyPopupService
                    .open(HobbySuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
