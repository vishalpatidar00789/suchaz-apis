/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz-delete-dialog.component';
import { TraitImageSuchazService } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.service';

describe('Component Tests', () => {

    describe('TraitImageSuchaz Management Delete Component', () => {
        let comp: TraitImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<TraitImageSuchazDeleteDialogComponent>;
        let service: TraitImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitImageSuchazDeleteDialogComponent],
                providers: [
                    TraitImageSuchazService
                ]
            })
            .overrideTemplate(TraitImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
