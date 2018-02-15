import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { HobbySuchaz } from './hobby-suchaz.model';
import { HobbySuchazPopupService } from './hobby-suchaz-popup.service';
import { HobbySuchazService } from './hobby-suchaz.service';

@Component({
    selector: 'jhi-hobby-suchaz-delete-dialog',
    templateUrl: './hobby-suchaz-delete-dialog.component.html'
})
export class HobbySuchazDeleteDialogComponent {

    hobby: HobbySuchaz;

    constructor(
        private hobbyService: HobbySuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.hobbyService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'hobbyListModification',
                content: 'Deleted an hobby'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-hobby-suchaz-delete-popup',
    template: ''
})
export class HobbySuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private hobbyPopupService: HobbySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.hobbyPopupService
                .open(HobbySuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
